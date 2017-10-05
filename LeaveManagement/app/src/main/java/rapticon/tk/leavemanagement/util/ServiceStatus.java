package rapticon.tk.leavemanagement.util;

public enum ServiceStatus {

    OK(1),JSON_EXCEPTION(2),IO_EXCEPTION(3),NULL_EX(4),NO_INTERNET(5),SERVICE_DOWN(6);

    private int status;

    ServiceStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
