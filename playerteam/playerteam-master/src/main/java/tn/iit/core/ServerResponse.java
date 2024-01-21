package tn.iit.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerResponse <T>{

    private int code ;
    private String message ;
    private T data;

    public ServerResponse<T> success(int code, T data){
        return new ServerResponse<>(code,"sucess",data);
    }

    public ServerResponse<T> echec(int code, String message){
        return new ServerResponse<>(code,message,null);
    }
}
