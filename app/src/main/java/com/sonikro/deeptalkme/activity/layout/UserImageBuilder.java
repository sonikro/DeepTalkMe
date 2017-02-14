package com.sonikro.deeptalkme.activity.layout;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.sonikro.deeptalkme.model.User;

/**
 * Created by Jonathan Nagayoshi on 19/07/2016.
 */
public class UserImageBuilder {
    public static TextDrawable buildUserDrawable(User user)
    {
        ColorGenerator generator = ColorGenerator.MATERIAL;
        String userInitials = user.getInitials();
        int color = generator.getColor(userInitials);
        TextDrawable userImage = TextDrawable.builder().buildRound(userInitials,color);
        return userImage;
    }
}
