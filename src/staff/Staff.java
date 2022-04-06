package staff;

public class Staff {

    private String position;
    private Integer vacancyNumber;
    private String department;
    private Double salary;
    private Integer total;
    private String education;

    public Staff (String department, String position, Integer vacancyNumber, Integer total){
        this.department = department;
        this.position = position;
        this.vacancyNumber = vacancyNumber;
        this.total = total;
    }

    public Staff(String position, Integer vacancyNumber){
        this.setPosition(position);
        this.setVacancyNumber(vacancyNumber);
    }
    public Staff (String department){
        this.setDepartment(department);
    }
    public Staff(String position, Double salary){
        this.setPosition(position);
        this.setSalary(salary);
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getVacancyNumber() {
        return vacancyNumber;
    }

    public void setVacancyNumber(Integer vacancyNumber) {
        this.vacancyNumber = vacancyNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }
}
