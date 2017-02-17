/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /       
 */

package com.twilio.rest.api.v2010.account.recording.addonresult;

import com.twilio.base.Deleter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class PayloadDeleter extends Deleter<Payload> {
    private String pathAccountSid;
    private final String pathReferenceSid;
    private final String pathAddOnResultSid;
    private final String pathSid;

    /**
     * Construct a new PayloadDeleter.
     * 
     * @param pathReferenceSid The reference_sid
     * @param pathAddOnResultSid The add_on_result_sid
     * @param pathSid Delete by unique payload Sid
     */
    public PayloadDeleter(final String pathReferenceSid, 
                          final String pathAddOnResultSid, 
                          final String pathSid) {
        this.pathReferenceSid = pathReferenceSid;
        this.pathAddOnResultSid = pathAddOnResultSid;
        this.pathSid = pathSid;
    }

    /**
     * Construct a new PayloadDeleter.
     * 
     * @param pathAccountSid The account_sid
     * @param pathReferenceSid The reference_sid
     * @param pathAddOnResultSid The add_on_result_sid
     * @param pathSid Delete by unique payload Sid
     */
    public PayloadDeleter(final String pathAccountSid, 
                          final String pathReferenceSid, 
                          final String pathAddOnResultSid, 
                          final String pathSid) {
        this.pathAccountSid = pathAccountSid;
        this.pathReferenceSid = pathReferenceSid;
        this.pathAddOnResultSid = pathAddOnResultSid;
        this.pathSid = pathSid;
    }

    /**
     * Make the request to the Twilio API to perform the delete.
     * 
     * @param client TwilioRestClient with which to make the request
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public boolean delete(final TwilioRestClient client) {
        this.pathAccountSid = this.pathAccountSid == null ? client.getAccountSid() : this.pathAccountSid;
        Request request = new Request(
            HttpMethod.DELETE,
            Domains.API.toString(),
            "/2010-04-01/Accounts/" + this.pathAccountSid + "/Recordings/" + this.pathReferenceSid + "/AddOnResults/" + this.pathAddOnResultSid + "/Payloads/" + this.pathSid + ".json",
            client.getRegion()
        );
        
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Payload delete failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
        
            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }
        
        return response.getStatusCode() == 204;
    }
}