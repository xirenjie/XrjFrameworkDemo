package com.xrj.framework.nice.data;

/**
 * Created by a on 2017/6/16.
 *
 * @auther XRJ
 */
public class HttpResult<T> {
    private boolean error;
    private T results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
