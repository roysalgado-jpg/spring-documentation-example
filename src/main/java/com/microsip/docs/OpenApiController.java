package com.microsip.docs;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenApiController {

  @GetMapping(
      value = DocsPathSupport.DOCS_BASE + "/openapi.yaml",
      produces = "application/yaml")
  public String openapi() throws IOException {
    ClassPathResource resource = new ClassPathResource("docs/openapi/external-apis.doc.yaml");
    String yaml = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    return DocsPathSupport.applyGatewayDocsAssetPaths(yaml);
  }
}
