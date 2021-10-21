package bestflow.api.response;

public enum CodeResponse {
    Success("success"),
    Error("error");

    private String msg;

    CodeResponse(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
