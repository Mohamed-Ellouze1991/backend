package tn.iit.core;

import lombok.Data;

@Data
public class ServerResponse<T> {
    private int code;
    private String message;
    private T data;

    public ServerResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ServerResponse() {
    }

    public ServerResponse<T> success(int code, T data){
        return new ServerResponse<>(code,"sucess",data);
    }

    public ServerResponse<T> echec(int code, String message){
        return new ServerResponse<>(code,message,null);
    }
}
