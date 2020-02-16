package Film;
import tool.*;

public class Grade {
    public double star1 = 0;    //一星得分百分比
    public double star2 = 0;    //二星得分百分比
    public double star3 = 0;    //三星得分百分比
    public double star4 = 0;    //四星得分百分比
    public double star5 = 0;    //五星得分百分比

    public Grade(double star1, double star2, double star3, double star4, double star5) {
        this.star1 = star1;
        this.star2 = star2;
        this.star3 = star3;
        this.star4 = star4;
        this.star5 = star5;
    }

    public double getStar1() {
        return star1;
    }

    public void setStar1(double star1) {
        this.star1 = star1;
    }

    public double getStar2() {
        return star2;
    }

    public void setStar2(double star2) {
        this.star2 = star2;
    }

    public double getStar3() {
        return star3;
    }

    public void setStar3(double star3) {
        this.star3 = star3;
    }

    public double getStar4() {
        return star4;
    }

    public void setStar4(double star4) {
        this.star4 = star4;
    }

    public double getStar5() {
        return star5;
    }

    public void setStar5(double star5) {
        this.star5 = star5;
    }


}
