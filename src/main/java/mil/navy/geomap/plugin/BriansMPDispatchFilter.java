package mil.navy.geomap.plugin;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class BriansMPDispatchFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
//		if (request instanceof HttpServletRequest) {
//			request = new BriansAdvancedDispatchHttpRequest((HttpServletRequest) request);
//        }
        chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	static class BriansAdvancedDispatchHttpRequest extends HttpServletRequestWrapper {
        
        String servletPath = null;
        
        public BriansAdvancedDispatchHttpRequest(HttpServletRequest delegate) {
            super(delegate);
            
            if (delegate.getClass().getSimpleName().endsWith("MockHttpServletRequest")) {
                return;
            }
            
            String path = delegate.getPathInfo();
            
            if (path == null) {
                return;
            }
            
//            int slash = path.indexOf('/', 1);
//            if (slash > -1 ) {
//                this.servletPath = path.substring(0, slash);
//            }
//            else {
            this.servletPath = path;
//            }
            
            int question = this.servletPath.indexOf('?');
            if (question > -1 ) {
                this.servletPath = this.servletPath.substring(0, question);
            }
            
        }
        
        public String getPathInfo() {
            HttpServletRequest delegate = (HttpServletRequest) getRequest();
            if (servletPath != null &&
                    delegate.getPathInfo() != null &&
                    delegate.getPathInfo().startsWith(servletPath))
                return delegate.getPathInfo().substring(servletPath.length());
            else
                return delegate.getPathInfo();
        }

        public String getServletPath() {
            return servletPath != null ? 
                servletPath : ((HttpServletRequest)getRequest()).getServletPath();
        }
    }

}
