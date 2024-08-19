package Classes;

public class Procedure {
    private String procedureName;
    private String procedureDate;
    private String procedureAuthor;
    private String procedureDescription;

    public Procedure(String procedureName, String procedureDate, String procedureAuthor, String procedureDescription) {
        this.procedureName = procedureName;
        this.procedureDate = procedureDate;
        this.procedureAuthor = procedureAuthor;
        this.procedureDescription = procedureDescription;
    }

    public String getProcedureName() { return procedureName; }
    public void setProcedureName(String procedureName) { this.procedureName = procedureName; }

    public String getProcedureDate() { return procedureDate; }
    public void setProcedureDate(String procedureDate) { this.procedureDate = procedureDate; }

    public String getProcedureAuthor() { return procedureAuthor; }
    public void setProcedureAuthor(String procedureAuthor) { this.procedureAuthor = procedureAuthor; }

    public String getProcedureDescription() { return procedureDescription; }
    public void setProcedureDescription(String procedureDescription) { this.procedureDescription = procedureDescription; }

    @Override
    public String toString() {
        return "\nName: " + procedureName + "\nDate: " + procedureDate + "\nAuthor: " + procedureAuthor + "\nDescription:\n " + procedureDescription;
    }
}
