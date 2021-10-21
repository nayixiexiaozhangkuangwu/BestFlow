package bestflow.api.response;

import lombok.Data;

@Data
public class RestResponse {
    private String code;
    private String msg;
    private Object data;

    public static RestResponse success() {
        RestResponse restResponse = new RestResponse();
        restResponse.setCode(CodeResponse.Success.name());

        return restResponse;
    }

    public static RestResponse success(Object data) {
        RestResponse restResponse = new RestResponse();
        restResponse.setCode(CodeResponse.Success.name());
        restResponse.setData(data);

        return restResponse;
    }

    public static RestResponse error(String msg) {
        RestResponse restResponse = new RestResponse();
        restResponse.setCode(CodeResponse.Error.name());
        restResponse.setMsg(msg);

        return restResponse;
    }
}
