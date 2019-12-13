package in.resumuff.core.users.entities;

public enum Permission {
    
    POST_COMMENT, DELETE_COMMENT, RATE_COMMENT, UPLOAD_RESUME, START_NEW_THREAD;
    
    public boolean checkPermission(Role role){
        switch(this){
            case POST_COMMENT:
                return role.getCanPostComments();
            case DELETE_COMMENT:
                return role.getCanDeleteComments();
            case RATE_COMMENT:
                return role.getCanRateComments();
            case UPLOAD_RESUME:
                return role.getCanUploadResume();
            case START_NEW_THREAD:
                return role.getCanStartNewThread();
        }
        
        throw new IllegalStateException("Unknown permission node");
    }
    
}
