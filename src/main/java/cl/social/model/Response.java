package cl.social.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Response<E> {
    
    public Response(E body) {
        super();
        this.body = body;
    }
    
    private E body;
    
}
