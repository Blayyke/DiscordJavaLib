package me.xa5.discordjavalib.entities;

import me.xa5.discordjavalib.entities.impl.UserImpl;

public class BotUserImpl extends UserImpl implements BotUser {
    private final boolean verified;
    private final boolean mfaEnabled;

    public BotUserImpl(DiscordApi api, String name, boolean mfaEnabled, String id, String discriminator, boolean verified, Icon.UserIcon avatar) {
        super(api, id, discriminator, true, name, avatar);
        this.verified = verified;
        this.mfaEnabled = mfaEnabled;
    }

    @Override
    public boolean isVerified() {
        return verified;
    }

    @Override
    public boolean isMFAEnabled() {
        return mfaEnabled;
    }
}