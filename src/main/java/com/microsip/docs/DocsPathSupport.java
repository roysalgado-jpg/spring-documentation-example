package com.microsip.docs;

final class DocsPathSupport {

  static final String GATEWAY_PREFIX = "/apigateway/v1";
  static final String DOCS_BASE = GATEWAY_PREFIX + "/docs";

  private DocsPathSupport() {}

  static String applyGatewayDocsAssetPaths(String content) {
    return content
        .replace("src=\"/docs/", "src=\"" + DOCS_BASE + "/")
        .replace("href=\"/docs/", "href=\"" + DOCS_BASE + "/")
        .replace("url(/docs/", "url(" + DOCS_BASE + "/");
  }
}
