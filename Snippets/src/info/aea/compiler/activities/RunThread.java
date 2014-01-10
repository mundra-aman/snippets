package info.aea.compiler.activities;

import info.aea.compiler.exceptions.AuthException;
import info.aea.compiler.exceptions.DataException;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class RunThread extends Thread{
		
		protected Handler handler;
		protected int mState;
		protected int status = 0;
		
		final static int STATE_DONE = 0;
        final static int STATE_RUNNING = 1;
        
        protected String source = "";
        protected String input = "";
        protected int lang = 1;
        protected Context activity;
		
		public RunThread(Context context, Handler handler){
			this.handler = handler;
			this.activity = context;
		}
		
		public void setLang(int lang) {
			this.lang = lang;
		}

		public void setInput(String input) {
			this.input = input;
		}

		public void setSource(String source) {
			this.source = source;
		}

		public void run() {
			
            mState = STATE_RUNNING;
            handle("open", "");
            
            Ideone ideone = new Ideone(activity);
            boolean priv = false;
            
            // create ideone link
            String link = null;
            try{
            	link = ideone.createSubmission(source, lang, input, true, priv);
            	if( link == null ) throw new Exception("null");
            	handle("echo", link);
            } catch( AuthException e ){
            	handle("error", "Auth error");
            	return;
            } catch( DataException e ){
            	handle("error", "Error #1");
            	return;
            } catch( Exception e ){
            	handle("error", "Error #2");
            	return;
            }
            
            // wait
            while (mState == STATE_RUNNING) {
                
            	// interrupt
            	try { Thread.sleep(1000); } catch (InterruptedException e) {}
            	
            	IdeoneSubmissionStatus iss = null;
                try {
                	iss = ideone.getSubmissionStatus(link);
	                if( iss == null ){
	                	handle("error", "Error #3");
	                    continue;
	                }
                } catch( AuthException e ){
                	handle("error", "Auth error");
                } catch( DataException e ){
                	handle("error", "Error #4");
                }
	            status = iss.status;
                
	            if( status == 0 ){ this.mState = STATE_DONE; }
	            handle("echo", Ideone.translateState(status));
            }
            
            // get full info
            try {
            IdeoneSubmissionDetails isd = ideone.getSubmissionDetails(link, false, false, true, true, true);
	            if( isd != null ){
	            	String out = isd.output;
	            	if( isd.cmpinfo != null && !isd.cmpinfo.equals("") ){
	            		out = isd.cmpinfo + "\n" + out;
	            	}
	            	if( isd.stderr != null && !isd.stderr.equals("") ){
	            		out = out + "\n---" + isd.stderr;
	            	}
	            	
	            	handle("echo", Ideone.translateResult(isd.result));
	            	handle("result", Ideone.translateResult(isd.result));
	            	handle("echo2", out);
	            }
            } catch( AuthException e ){
            	handle("error", "Auth error");
            }
            
            // hide dialog
            handle("close", "");
        }
        
		/**
		 * return data to main thread
		 * 
		 * @param command
		 * @param text
		 */
		protected void handle(String command, String text){
			Message msg = handler.obtainMessage();
        	Bundle b = new Bundle();
			b.putString("command", command);
			b.putString("text", text);
			msg.setData(b);
            handler.sendMessage(msg);
		}

		/* sets the current state for the thread,
         * used to stop the thread */
        public void setState(int state) {
            mState = state;
        }
	}