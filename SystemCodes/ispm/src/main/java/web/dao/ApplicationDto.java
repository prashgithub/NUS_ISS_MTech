package web.dao;

public class ApplicationDto {
    private String age = "0";
    private String id = "0";
    private String name = "";
    private String issuer="";
    private String status = "";
    private String gender="";
    private String ans1="0";
    private String ans2="0";
    private String ans3="0";
    private String ans4="0";
    private String ans5="0";
    private String pre1="3";
    private String pre2="3";
    private String pre3="3";
    private String pre4="3";
    private String pre5="3";

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAnswer(Long qid, String ans, String pre) {
        if(qid == 1){
            this.ans1 = ans;
            this.pre1 = pre;
        }
        else if (qid == 2){
            this.ans2 = ans;
            this.pre2 = pre;
        }
        else if (qid == 3){
            this.ans3 = ans;
            this.pre3 = pre;
        }
        else if (qid == 4){
            this.ans4 = ans;
            this.pre4 = pre;
        }
        else if (qid == 5){
            this.ans5 = ans;
            this.pre5 = pre;
        }
    }

    public String getAns1() {
        return ans1;
    }

    public String getAns2() {
        return ans2;
    }

    public String getAns3() {
        return ans3;
    }

    public String getAns4() {
        return ans4;
    }

    public String getAns5() {
        return ans5;
    }

    public String getPre1() {
        return pre1;
    }

    public String getPre2() {
        return pre2;
    }

    public String getPre3() {
        return pre3;
    }

    public String getPre4() {
        return pre4;
    }

    public String getPre5() {
        return pre5;
    }
}
