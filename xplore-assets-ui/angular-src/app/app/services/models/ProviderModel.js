/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

'use strict';

/**
 * @ngdoc service
 * @name myApp.ProviderModel
 * @description
 * # ProviderModel
 * {
 *    providerKey: number,
 *    providerName: string,
 *    providerType: ProviderTypeCode,
 *    description: string,
 *    providerAddress: AddressModel,
 *    billingAddress: AddressModel,
 *    shippingAddress: AddressModel,
 *    phone: string,
 *    fax: string,
 *    email: string,
 *    webSiteUrl: string,
 *    logoUrl: string,
 *    defaultCurrency: CurrencyCode,
 *    contacts: [...ProviderContactModel],
 *    memberSince: string,
 *    providerRating: number,
 *    jobsCompleted: number
 * }
 * Factory in the myApp.
 */
angular.module('myApp')
    .factory('ProviderModel', [
        'AddressModel', 'ProviderContactModel',
        function (AddressModel, ProviderContactModel) {

            function Provider(data) {
                //region Model properties
                this.providerKey = null;
                this.providerName = null;
                this.providerType = null;
                this.description = null;
                this.providerAddress = new AddressModel();
                this.billingAddress = new AddressModel();
                this.shippingAddress = new AddressModel();
                this.phone = null;
                this.fax = null;
                this.email = null;
                this.webSiteUrl = null;
                this.logoUrl = null;
                this.defaultCurrency = null;
                this.contacts = new ProviderContactModel();
                this.memberSince = null;
                this.providerRating = null;
                this.jobsCompleted = null;
                //endregion

                if (data) {
                    this.setData(data);
                }
            }

            Provider.prototype = {
                setData: function (data) {
                    angular.extend(this, data, this);
                }
            };

            return Provider;

        }
    ]);
