package notificator.company.business.unii.mrroll.service.model;


public class CreateUserResponseWithCode {
    private final CreateUserResponse createUserResponse;
    private final int statusCode;

    public CreateUserResponseWithCode(CreateUserResponse createUserResponse, int statusCode) {
        this.createUserResponse = createUserResponse;
        this.statusCode = statusCode;
    }

    public CreateUserResponse getCreateUserResponse() {
        return createUserResponse;
    }

    public int getStatusCode() {
        return statusCode;
    }
}