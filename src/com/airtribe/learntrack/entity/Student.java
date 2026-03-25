package src.com.airtribe.learntrack.entity;

public class Student extends Person {

    private String batch;
    private boolean active;

    public Student() {
        super();
    }

    // Constructor to create a student without email
    public Student(int id, String firstName, String lastName, String batch) {
        super(id, firstName, lastName, null);
        this.batch = batch;
        this.active = true;
    }

    // Constructor to create student with email

    public Student(int id, String firstName, String lastName, String email, String batch) {
        super(id, firstName, lastName, email);
        this.batch = batch;
        this.active = true;
    }

    @Override
    public String getDisplayName() {

        // return getFirstName() + " " + getLastName() + " ( Batch: " + batch + ")";
        return super.getDisplayName() + " ( Batch: " + batch + ")";
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}