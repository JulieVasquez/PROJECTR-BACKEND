package pe.du.upc.projectrback.dtos;

public class DTOToken {
    private String jwtoken;
    private Long id;
    private String authorities;

    public DTOToken() {
    }

    public DTOToken(String jwtoken, Long id, String authorities) {
        this.jwtoken = jwtoken;
        this.id = id;
        this.authorities = authorities;
    }

    public String getJwtoken() {
        return jwtoken;
    }

    public void setJwtoken(String jwtoken) {
        this.jwtoken = jwtoken;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }
}
