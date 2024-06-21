package com.team4.museum.controller;

import com.team4.museum.controller.action.Action;
import com.team4.museum.controller.action.IndexAction;
import com.team4.museum.controller.action.admin.*;
import com.team4.museum.controller.action.gallery.*;
import com.team4.museum.controller.action.notice.*;

public class ActionFactory {

    private ActionFactory() {
    }

    private static final ActionFactory instance = new ActionFactory();

    public static ActionFactory getInstance() {
        return instance;
    }

    public Action getAction(String command) {
        return switch (command != null ? command : "") {

            // index action
            case "", "index" -> new IndexAction();

            // user gallery
            case "galleryList" -> new GalleryListAction();
            case "galleryView" -> new GalleryViewAction();
            case "galleryWriteForm" -> new GalleryWriteFormAction();
            case "galleryWrite" -> new GalleryWriteAction();

            case "galleryUpdateForm" -> new GalleryUpdateFormAction();
            case "galleryUpdate" -> new GalleryUpdateAction();
            case "galleryDelete" -> new GalleryDeleteAction();

            // notice
            case "noticeList" -> new NoticeListAction();
            case "noticeView" -> new NoticeViewAction();
            case "insertNoticeForm" -> new InsertNoticeFormAction();
            case "insertNotice" -> new InsertNoticeAction();
            case "updateNoticeForm" -> new UpdateNoticeFormAction();
            case "updateNotice" -> new UpdateNoticeAction();
            case "noticeViewWithoutCnt" -> new NoticeViewWithoutCntAction();
            case "deleteNotice" -> new DeleteNoticeAction();

            // admin
            case "admin" -> new AdminMainAction();
            case "grantAdminRights" -> new GrantAdminRightsAction();
            case "adminMemberList" -> new AdminMemberListAction();
            case "adminArtworkList" -> new AdminArtworkListAction();
            case "adminNoticeList" -> new AdminNoticeListAction();
            case "adminGalleryList" -> new AdminGalleryListAction();
            case "adminQnaList" -> new AdminQnaListAction();
            case "adminDeleteMember" -> new AdminDeleteMemberAction();
            case "adminDeleteArtwork" -> new AdminDeleteArtworkAction();
            case "adminDeleteNotice" -> new AdminDeleteNoticeAction();
            case "adminDeleteGallery" -> new AdminDeleteGalleryAction();
            case "adminDeleteQna" -> new AdminDeleteQnaAction();
            case "adminDbReset" -> new AdminDbResetAjaxAction();

            // default
            default -> null;

        };
    }

}
