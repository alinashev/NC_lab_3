package lab_3.models.pojo;

import java.util.Objects;

public class Ticket {
    private int ticketId;
    private String bugStatus;
    private String severity;
    private String priority;
    private String name;
    private String summary;
    private String date;
    private String expected;
    private String actual;

    public Ticket(int ticketId, String bugStatus, String severity, String priority,
                  String name, String summary, String date, String expected, String actual) {
        this.ticketId = ticketId;
        this.bugStatus = bugStatus;
        this.severity = severity;
        this.priority = priority;
        this.name = name;
        this.summary = summary;
        this.date = date;
        this.expected = expected;
        this.actual = actual;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getBugStatus() {
        return bugStatus;
    }

    public void setBugStatus(String bugStatus) {
        this.bugStatus = bugStatus;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return ticketId == ticket.ticketId && Objects.equals(bugStatus, ticket.bugStatus) && Objects.equals(severity, ticket.severity) && Objects.equals(priority, ticket.priority) && Objects.equals(name, ticket.name) && Objects.equals(summary, ticket.summary) && Objects.equals(date, ticket.date) && Objects.equals(expected, ticket.expected) && Objects.equals(actual, ticket.actual);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, bugStatus, severity, priority, name, summary, date, expected, actual);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", bugStatus='" + bugStatus + '\'' +
                ", severity='" + severity + '\'' +
                ", priority='" + priority + '\'' +
                ", name='" + name + '\'' +
                ", summary='" + summary + '\'' +
                ", date='" + date + '\'' +
                ", expected='" + expected + '\'' +
                ", actual='" + actual + '\'' +
                '}';
    }
}
