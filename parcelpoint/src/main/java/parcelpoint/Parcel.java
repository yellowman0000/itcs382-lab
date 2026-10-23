package parcelpoint;

public class Parcel {
    private String code;
    private String studentId;
    private int size;          // 1 = small, 2 = medium, 3 = large
    private String status;

    public Parcel(String code, String studentId, int size) {
        this.code = code;
        this.studentId = studentId;
        this.size = size;
        this.status = "NEW";
    }

    public String getCode() {
        return code;
    }

    public String getStudentId() {
        return studentId;
    }

    public int getSize() {
        return size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double fee(int days) {
        if (days <= 3) {
            return 0;
        }
        if (size == 1) {
            return (days - 3) * 10.0;
        } else if (size == 2) {
            return (days - 3) * 15.0;
        }
        return (days - 3) * 20.0;
    }
}
