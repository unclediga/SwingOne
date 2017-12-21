package unclediga.data;
public class ExcelPerson {
    private String acc;
    private String fio;
    private String dept;
    private Double sum;
    private String ssn;

    public ExcelPerson(String fio, String dept, String acc, Double sum) {
        this.acc = acc;
        this.fio = fio;
        this.dept = dept;
        this.sum = sum;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    @Override
    public String toString() {
        return "ExcelPerson{" +
                "acc='" + acc + '\'' +
                ", fio='" + fio + '\'' +
                ", dept='" + dept + '\'' +
                ", sum='" + sum + '\'' +
                ", ssn='" + ssn + '\'' +
                '}';
    }
}
