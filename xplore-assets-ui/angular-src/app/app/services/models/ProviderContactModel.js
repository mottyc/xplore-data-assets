/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

'use strict';

/**
 * @ngdoc service
 * @name myApp.ProviderContactModel
 * @description
 * # ProviderContactModel
 * {
 *    mailNotifications: [...ProviderNotificationCode],
 *    textNotifications: [...ProviderNotificationCode],
 *    key: number,
 *    parentKey: number,
 *    prefix: string,
 *    firstName: string,
 *    lastName: string,
 *    title: string,
 *    description: string,
 *    email: string,
 *    phone: string,
 *    mobile: string,
 *    fax: string,
 *    mailingAddress: AddressModel,
 *    birthday: string,
 *    nationality: CountryCode,
 *    userType: UserTypeCode,
 *    userStatus: UserStatusCode
 * }
 * Factory in the myApp.
 */
angular.module('myApp')
    .factory('ProviderContactModel', [
        'AddressModel',
        function (AddressModel) {

            function ProviderContact(contactData) {
                //region Model properties
                this.mailNotifications = [];
                this.textNotifications = [];
                this.key = null;
                this.parentKey = null;
                this.prefix = null;
                this.firstName = null;
                this.lastName = null;
                this.title = null;
                this.description = null;
                this.email = null;
                this.phone = null;
                this.mobile = null;
                this.fax = null;
                this.mailingAddress = new AddressModel();
                this.birthday = null;
                this.nationality = null;
                this.userType = null;
                this.userStatus = null;
                //endregion

                if (contactData) {
                    this.setData(contactData);
                }
            }

            ProviderContact.prototype = {

                setData: function (contactData) {
                    angular.extend(this, contactData, this);
                }

            };

            return ProviderContact;
        }
    ]);