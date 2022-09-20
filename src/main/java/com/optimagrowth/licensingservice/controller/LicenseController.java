package com.optimagrowth.licensingservice.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.optimagrowth.licensingservice.model.License;
import com.optimagrowth.licensingservice.service.LicenseService;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "v1/organization/{organizationId}/license")
@RequiredArgsConstructor
public class LicenseController {

  private final LicenseService licenseService;

  @GetMapping(value = "/{licenseId}")
  public ResponseEntity<License> getLicense(
      @PathVariable("organizationId") String organizationId,
      @PathVariable("licenseId") String licenseId,
      @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE, required = false) Locale locale) {
    License license = licenseService.getLicense(licenseId, organizationId);

    license.add(linkTo(methodOn(LicenseController.class)
            .getLicense(organizationId, license.getLicenseId(), locale))
            .withRel("createLicense"),
        linkTo(methodOn(LicenseController.class)
            .updateLicense(organizationId, license, locale))
            .withRel("updateLicense"),
        linkTo(methodOn(LicenseController.class)
            .deleteLicense(organizationId, license.getLicenseId()))
            .withRel("deleteLicense"));

    return ResponseEntity.ok(license);
  }

  @PutMapping
  public ResponseEntity<String> updateLicense(
      @PathVariable("organizationId") String organizationId,
      @RequestBody License request,
      @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE, required = false) Locale locale) {
    return ResponseEntity.ok(licenseService.updateLicense(request, organizationId, locale));
  }

  @PostMapping
  public ResponseEntity<String> createLicense(
      @PathVariable("organizationId") String organizationId,
      @RequestBody License request,
      @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE, required = false) Locale locale) {
    return ResponseEntity.ok(licenseService.createLicense(request, organizationId, locale));
  }

  @DeleteMapping(value = "/{licenseId}")
  public ResponseEntity<String> deleteLicense(
      @PathVariable("organizationId") String organizationId,
      @PathVariable("licenseId") String licenseId) {
    return ResponseEntity.ok(licenseService.deleteLicense(licenseId, organizationId));
  }
}
