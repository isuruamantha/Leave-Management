package rapticon.tk.leavemanagement.util;

public enum ServiceURL {

    Login("UserLogin.php"),
    NEW_lEAVE("InsertNewLeave.php"),
    GET_ALL_LEAVES_BY_ID("GetAllLeavesFromUserId.php"),
    DELETE_LEAVE("DeleteLeaveFromLeaveId.php"),
    UPDATE_LEAVE("UpdateLeave.php"),
    GET_ALL_LEAVES("GetAllLeaves.php");

    private String url;

    ServiceURL(String url) {
        this.url = url;
    }

    /**
     * Gets url
     *
     * @return value of url
     */
    public String getUrl() {

        return "http://192.168.0.100/LeaveManagement/"+url; /*my laptop*/
//        return "http://192.168.8.103/LeaveManagement/" + url; /*my laptop*/
    }
}
