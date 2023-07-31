package com.demo.socialhub.view;

import com.demo.socialhub.model.view.UserProfileView;

public interface UserProfileViewService extends ViewService {
    UserProfileView constructView(int userId);
}
