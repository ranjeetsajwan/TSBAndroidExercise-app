package co.nz.tsb.interview.bankrecmatchmaker;

public class MatchItem {

    private final String paidTo;
    private final String transactionDate;
    private final float total;
    private final String docType;
    private boolean isChecked;


    public MatchItem(String paidTo, String transactionDate, float total, String docType, boolean isChecked) {
        this.paidTo = paidTo;
        this.transactionDate = transactionDate;
        this.total = total;
        this.docType = docType;
        this.isChecked = isChecked;
    }

    public String getPaidTo() {
        return paidTo;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public float getTotal() {
        return total;
    }

    public String getDocType() {
        return docType;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
