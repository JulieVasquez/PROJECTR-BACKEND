package pe.du.upc.projectrback.security;

import org.springframework.security.core.GrantedAuthority;
import pe.du.upc.projectrback.entities.Authority;

public class AuthoritySecurity implements GrantedAuthority{
    private Authority authority;
/*
Metodo  de GrantedAuthority
 */
    @Override
    public String getAuthority() {
        return authority.getName();
    }

/*
Metodos de acceso
 */
    public AuthoritySecurity(Authority authority) {
        this.authority = authority;
    }

    public AuthoritySecurity() {
    }



    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "AuthoritySecurity{" +
                "authority=" + authority +
                '}';
    }
}
