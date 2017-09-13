package com.xzh.netty.callback;

public class MyFetcher implements Fetcher {
    
	 Data data ;
	
	 
	public MyFetcher(Data data) {
		
		this.data = data;
	}


	public void fetcherData(FetcherCallback callback) {
		// TODO Auto-generated method stub
        try {
			callback.onData(data);
		} catch (Exception e) {
			callback.onError(e);
		}
	}

}
