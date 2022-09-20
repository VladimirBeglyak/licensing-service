package com.optimagrowth.licensingservice.service;

import com.optimagrowth.licensingservice.model.License;
import java.util.Locale;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LicenseService {

  public static final String RESPONSE_MESSAGE_UPDATE = "This is the put and the object is: %s";
  public static final String RESPONSE_MESSAGE_DELETE = "Deleting license with id %s for the organization %s";
  public static final String RESPONSE_MESSAGE_CREATE = "This is the post and the object is: %s";
  private final MessageSource messageSource;

  public License getLicense(String licenseId, String organizationId) {
    return License.builder()
        .id(UUID.randomUUID())
        .licenseId(licenseId)
        .organizationId(organizationId)
        .description("Software Product")
        .productName("Ostock")
        .licenseType("full")
        .build();
  }

  public String createLicense(License license, String organizationId, Locale locale) {
    String responseMessage = null;
    if (license != null) {
      license.setOrganizationId(organizationId);
      responseMessage = String.format(messageSource.getMessage("license.create.message", null, locale), license);
    }
    return responseMessage;
  }

  public String updateLicense(License license, String organizationId, Locale locale) {
    String responseMessage = null;
    if (license != null) {
      license.setOrganizationId(organizationId);
      responseMessage = String.format(messageSource.getMessage("license.update.message", null, locale), license);
    }
    return responseMessage;
  }

  public String deleteLicense(String licenseId, String organizationId) {
    String responseMessage = null;
    responseMessage = String.format(RESPONSE_MESSAGE_DELETE, licenseId, organizationId);
    return responseMessage;
  }
}
