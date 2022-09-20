package com.optimagrowth.licensingservice.model;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@ToString
@Builder
public class License extends RepresentationModel<License> {

  private UUID id;
  private String licenseId;
  private String description;
  private String organizationId;
  private String productName;
  private String licenseType;
}
