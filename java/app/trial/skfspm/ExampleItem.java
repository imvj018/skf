package app.trial.skfspm;

public class ExampleItem {
    private String mTono, mStore, mTodate, mRefdoc, mRefer, mVal;

    public ExampleItem(String tono, String store, String todate, String refdoc, String refer, String val) {

        mTono = tono;
        mStore = store;
        mTodate = todate;
        mRefdoc = refdoc;
        mRefer = refer;
        mVal = val;


    }


    public String gettono() {
        return mTono;
    }

    public String getstore() {
        return mStore;
    }

    public String gettodate() {
        return mTodate;
    }

    public String getrefdoc() {
        return mRefdoc;
    }

    public String getrefer() {
        return mRefer;
    }

    public String getval() {
        return mVal;
    }



}

