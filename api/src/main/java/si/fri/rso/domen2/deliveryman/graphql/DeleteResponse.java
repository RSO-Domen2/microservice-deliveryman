package si.fri.rso.domen2.deliveryman.graphql;

public class DeleteResponse {

    private boolean deleted;

    public DeleteResponse(boolean deleted) {
        this.deleted = deleted;
    }
    
    public boolean isDeleted() {
        return this.deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
