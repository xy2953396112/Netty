package com.xzh.netty.callback;



/**
 * Hello Netty!
 * 回调
 *
 */
public class App 
{   
	public void doWork(){
		Fetcher fetcher = new MyFetcher(new Data(1,0));
		fetcher.fetcherData(new FetcherCallback() {
			
			public void onError(Throwable cause) {
				// TODO Auto-generated method stub
				System.out.println("错误："+cause.getMessage());
			}
			
			public void onData(Data data) throws Exception {
				// TODO Auto-generated method stub
				System.out.println("Data："+data.toString());
			}
		});
	}
		
	
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    
        App w = new App();
        
        w.doWork();
    }
}
