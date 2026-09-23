package com.microsip.docs;

import com.scalar.maven.core.ScalarProperties;
import com.scalar.maven.core.config.ScalarAgentOptions;
import com.scalar.maven.core.config.ScalarMcpOptions;
import com.scalar.maven.webmvc.ScalarWebMvcController;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocsScalarController extends ScalarWebMvcController {

  private final String customCss;

  public DocsScalarController() throws IOException {
    ClassPathResource resource = new ClassPathResource("docs/assets/scalar-custom.css");
    String css = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    this.customCss = DocsPathSupport.applyGatewayDocsAssetPaths(css);
  }

  @Override
  protected ScalarProperties configureProperties(
      ScalarProperties properties, HttpServletRequest request) {
    properties.setCustomCss(customCss);
    ScalarAgentOptions agent = new ScalarAgentOptions();
    agent.setDisabled(true);
    properties.setAgent(agent);
    ScalarMcpOptions mcp = new ScalarMcpOptions();
    mcp.setDisabled(true);
    properties.setMcp(mcp);
    properties.setTelemetry(false);
    return properties;
  }
}
