package com.github.dreamhead.moco.handler.proxy;

import static com.github.dreamhead.moco.util.Preconditions.checkNotNullOrEmpty;
import static com.github.dreamhead.moco.util.URLs.join;
import static com.github.dreamhead.moco.util.URLs.toBase;
import static com.google.common.base.Strings.nullToEmpty;

public class ProxyConfig {
    private final String localBase;
    private final String remoteBase;

    public ProxyConfig(final String localBase, final String remoteBase) {
        this.localBase = localBase;
        this.remoteBase = remoteBase;
    }

    public String localBase() {
        return this.localBase;
    }

    public String remoteBase() {
        return this.remoteBase;
    }

    public boolean canAccessedBy(final String uri) {
        return uri.startsWith(localBase);
    }

    public String remoteUrl(final String uri) {
        String relative = nullToEmpty(uri.replaceFirst(this.localBase, ""));
        return join(remoteBase, relative);
    }

    public static Builder builder(final String localBase) {
        return new Builder(localBase);
    }

    public static class Builder {
        private final String localBase;

        public Builder(String localBase) {
            this.localBase = localBase;
        }

        public ProxyConfig to(final String remoteBase) {
            return new ProxyConfig(toBase(localBase), toBase(checkNotNullOrEmpty(remoteBase, "Remote base should not be null")));
        }
    }
}
