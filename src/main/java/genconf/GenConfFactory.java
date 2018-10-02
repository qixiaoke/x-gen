package genconf;

import genconf.implementors.GenConfImplementor;

public class GenConfFactory {

    private GenConfFactory() {

    }

    public static GenConfEbi createGenConfEbi(GenConfImplementor provider) {
        return GenConfEbo.getInstance(provider);
    }
}
