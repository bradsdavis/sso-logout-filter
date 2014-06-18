sso-logout-filter
=================
SSO Logout Filter for redirecting SSO Logouts and invalidating the user's session.


To leverage:

  <filter>
    <filter-name>Static SSO Filter</filter-name>
    <filter-class>com.rhc.sso.SSOStaticLogoutFilter</filter-class>
    <init-param>
      <param-name>static.logout.url</param-name>
      <param-value>http://example.com</param-value>
    </init-param>
   </filter>
...

  <filter-mapping>
    <filter-name>Static SSO Filter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>

or, if from the header:

  <filter>
    <filter-name>Header SSO Filter</filter-name>
    <filter-class>com.rhc.sso.SSOHeaderLogoutFilter</filter-class>
    <init-param>
      <param-name>static.logout.url</param-name>
      <param-value>SOME_HEADER_PROPERTY</param-value>
    </init-param>
   </filter>
...

  <filter-mapping>
    <filter-name>Header SSO Filter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>

