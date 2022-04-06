package staff;

public class Worker {

    private Integer ID;
    private String FIO;
    private String address;
    private String phone;
    private String birthdate;
    private Integer nationalityID;
    private String sex;
    private Integer educationID;
    private Integer dependent;
    private String hiringDate;
    private String firingDate;
    private Double salary;
    private String UIN;
    private Integer pensionFundID;
    private Integer positionID;
    private Integer departmentID;
    private Integer experience;

    public Worker(String FIO){
        this.setFIO(FIO);
    }

    public Worker(String FIO, Integer experience){
        this.setFIO(FIO);
        this.setExperience(experience);
    }

    public Worker (Integer ID, String FIO, String address, String phone,
                   String birthdate, Integer nationalityID, String sex,Integer educationID,
                   Integer dependent, String hiringDate, String firingDate, Double salary,
                   String UIN, Integer pensionFundID, Integer positionID, Integer departmentID){
        this.setID(ID);
        this.setFIO(FIO);
        this.setAddress(address);
        this.setPhone(phone);
        this.setBirthdate(birthdate);
        this.setNationalityID(nationalityID);
        this.setSex(sex);
        this.setEducationID(educationID);
        this.setDependent(dependent);
        this.setHiringDate(hiringDate);
        this.setFiringDate(firingDate);
        this.setSalary(salary);
        this.setUIN(UIN);
        this.setPensionFundID(pensionFundID);
        this.setPositionID(positionID);
        this.setDepartmentID(departmentID);
    }


    public Worker (String FIO, String address, String phone,
                   String birthdate, Integer nationalityID, String sex,Integer educationID,
                   Integer dependent, String UIN, Integer pensionFundID){
        this.setFIO(FIO);
        this.setAddress(address);
        this.setPhone(phone);
        this.setBirthdate(birthdate);
        this.setNationalityID(nationalityID);
        this.setSex(sex);
        this.setEducationID(educationID);
        this.setDependent(dependent);
        this.setUIN(UIN);
        this.setPensionFundID(pensionFundID);
    }



    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public Integer getNationalityID() {
        return nationalityID;
    }

    public void setNationalityID(Integer nationalityID) {
        this.nationalityID = nationalityID;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getEducationID() {
        return educationID;
    }

    public void setEducationID(Integer educationID) {
        this.educationID = educationID;
    }

    public Integer getDependent() {
        return dependent;
    }

    public void setDependent(Integer dependent) {
        this.dependent = dependent;
    }

    public String getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(String hiringDate) {
        this.hiringDate = hiringDate;
    }

    public String getFiringDate() {
        return firingDate;
    }

    public void setFiringDate(String firingDate) {
        this.firingDate = firingDate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getUIN() {
        return UIN;
    }

    public void setUIN(String UIN) {
        this.UIN = UIN;
    }

    public Integer getPensionFundID() {
        return pensionFundID;
    }

    public void setPensionFundID(Integer pensionFundID) {
        this.pensionFundID = pensionFundID;
    }

    public Integer getPositionID() {
        return positionID;
    }

    public void setPositionID(Integer positionID) {
        this.positionID = positionID;
    }

    public Integer getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(Integer departmentID) {
        this.departmentID = departmentID;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }
}
