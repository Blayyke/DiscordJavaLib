package me.xa5.discordjavalib.event;

import me.xa5.discordjavalib.event.guild.category.CategoryEvent;
import me.xa5.discordjavalib.event.guild.category.EventChannelCategoryCreate;
import me.xa5.discordjavalib.event.guild.category.EventChannelCategoryDelete;
import me.xa5.discordjavalib.event.guild.channel.*;
import me.xa5.discordjavalib.event.guild.member.EventMemberUpdate;
import me.xa5.discordjavalib.event.guild.member.EventPresenceUpdate;
import me.xa5.discordjavalib.event.guild.message.EventMessageCreate;
import me.xa5.discordjavalib.event.guild.message.EventMessageDelete;
import me.xa5.discordjavalib.event.guild.role.EventRoleCreate;
import me.xa5.discordjavalib.event.guild.role.EventRoleDelete;
import me.xa5.discordjavalib.event.guild.role.EventRoleUpdate;
import me.xa5.discordjavalib.event.guild.role.RoleEvent;

public class EventListener {
    public final void onEvent(Event event) {
        if (event instanceof ReadyEvent) onReady((ReadyEvent) event);
        if (event instanceof EventMemberUpdate) onMemberUpdate((EventMemberUpdate) event);
        if (event instanceof EventPresenceUpdate) onPresenceUpdate((EventPresenceUpdate) event);

        if (event instanceof RoleEvent) onRoleEvent((RoleEvent) event);
        if (event instanceof EventRoleCreate) onRoleCreate((EventRoleCreate) event);
        if (event instanceof EventRoleDelete) onRoleDelete((EventRoleDelete) event);
        if (event instanceof EventRoleUpdate) onRoleUpdate((EventRoleUpdate) event);

        if (event instanceof CategoryEvent) onCategoryEvent((CategoryEvent) event);
        if (event instanceof EventChannelCategoryCreate) onCategoryCreate((EventChannelCategoryCreate) event);
        if (event instanceof EventChannelCategoryDelete) onCategoryDelete((EventChannelCategoryDelete) event);

        if (event instanceof ChannelEvent) onChannelEvent((ChannelEvent) event);
        if (event instanceof EventVoiceChannelCreate) onVoiceChannelCreate((EventVoiceChannelCreate) event);
        if (event instanceof EventVoiceChannelDelete) onVoiceChannelDelete((EventVoiceChannelDelete) event);
        if (event instanceof EventTextChannelCreate) onTextChannelCreate((EventTextChannelCreate) event);
        if (event instanceof EventTextChannelDelete) onTextChannelDelete((EventTextChannelDelete) event);

        if (event instanceof EventMessageCreate) onMessageCreate((EventMessageCreate) event);
        if (event instanceof EventMessageDelete) onMessageDelete((EventMessageDelete) event);
    }

    private void onMessageDelete(EventMessageDelete event) {
    }

    private void onMessageCreate(EventMessageCreate event) {
    }

    public void onTextChannelDelete(EventTextChannelDelete event) {
    }

    public void onTextChannelCreate(EventTextChannelCreate event) {
    }

    public void onVoiceChannelDelete(EventVoiceChannelDelete event) {
    }

    public void onVoiceChannelCreate(EventVoiceChannelCreate event) {
    }

    public void onChannelEvent(ChannelEvent event) {
    }

    public void onCategoryEvent(CategoryEvent event) {
    }

    public void onCategoryDelete(EventChannelCategoryDelete event) {
    }

    public void onCategoryCreate(EventChannelCategoryCreate event) {
    }

    public void onRoleEvent(RoleEvent event) {
    }

    public void onRoleUpdate(EventRoleUpdate event) {
    }

    public void onRoleDelete(EventRoleDelete event) {
    }

    public void onRoleCreate(EventRoleCreate event) {
    }

    public void onPresenceUpdate(EventPresenceUpdate event) {
    }

    public void onReady(ReadyEvent event) {
    }

    public void onMemberUpdate(EventMemberUpdate event) {

    }
}