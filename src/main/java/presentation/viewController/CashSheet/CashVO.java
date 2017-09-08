package presentation.viewController.CashSheet;

import javafx.beans.property.*;

/**
 * Created by YZ on 2017/9/5.
 */
public class CashVO {
    String project;
    int line_no;
    double year;
    double period;
    String formula;

    public CashVO(String project,int line_no,double year,double period,String formula){
        this.project=project;
        this.line_no=line_no;
        this.year=year;
        this.period=period;
        this.formula=formula;
    }

    public String getProject(){
        return project;
    }
    public void setProject(String project){
        this.project = project;
    }
    public StringProperty getProjectProperty(){
        return new SimpleStringProperty(project);
    }

    public int getline_no(){
        return line_no;
    }
    public void setline_no(int line_no){
        this.line_no=line_no;
    }
    public IntegerProperty line_noProperty(){
        return new SimpleIntegerProperty(line_no);
    }

    public double getYear(){
        return year;
    }
    public void setYear(double year){
        this.year=year;
    }
    public DoubleProperty getYearProperty(){
        return new SimpleDoubleProperty(year);
    }

    public double getPeriod(){
        return period;
    }
    public void setPeriod(double period){
        this.period=period;
    }
    public DoubleProperty getPeriodProperty(){
        return new SimpleDoubleProperty(period);
    }

    public String getFormula(){
        return formula;
    }
    public void setFormula(String formula){
        this.formula = formula;
    }
    public StringProperty getFormulaProperty(){
        return new SimpleStringProperty(formula);
    }
}
