package hudson.plugins.dry;

import org.jenkinsci.Symbol;
import org.kohsuke.stapler.QueryParameter;

import hudson.Extension;
import hudson.plugins.analysis.core.PluginDescriptor;
import hudson.util.FormValidation;

/**
 * Descriptor for the class {@link DryPublisher}. Used as a singleton. The
 * class is marked as public so that it can be accessed from views.
 *
 * @author Ulli Hafner
 */
@Extension(ordinal = 100) @Symbol("dry")
public final class DryDescriptor extends PluginDescriptor {
    /** The ID of this plug-in is used as URL. */
    static final String PLUGIN_ID = "dry";
    /** The URL of the result action. */
    static final String RESULT_URL = PluginDescriptor.createResultUrlName(PLUGIN_ID);
    /** Icons prefix. */
    static final String ICON_URL_PREFIX = "/plugin/dry/icons/";
    /** Icon to use for the result and project action. */
    static final String ICON_URL = ICON_URL_PREFIX + "dry-24x24.png";

    private static final ThresholdValidation VALIDATION = new ThresholdValidation();

    /**
     * Creates a new instance of {@link DryDescriptor}.
     */
    public DryDescriptor() {
        super(DryPublisher.class);
    }

    @Override
    public String getDisplayName() {
        return Messages.DRY_Publisher_Name();
    }

    @Override
    public String getPluginName() {
        return PLUGIN_ID;
    }

    @Override
    public String getIconUrl() {
        return ICON_URL;
    }

    @Override
    public String getSummaryIconUrl() {
        return ICON_URL_PREFIX + "dry-48x48.png";
    }

    /**
     * Performs on-the-fly validation on threshold for high warnings.
     *
     * @param highThreshold
     *            the threshold for high warnings
     * @param normalThreshold
     *            the threshold for normal warnings
     * @return the validation result
     */
    public FormValidation doCheckHighThreshold(@QueryParameter final String highThreshold, @QueryParameter final String normalThreshold) {
        return VALIDATION.validateHigh(highThreshold, normalThreshold);
    }

    /**
     * Performs on-the-fly validation on threshold for normal warnings.
     *
     * @param highThreshold
     *            the threshold for high warnings
     * @param normalThreshold
     *            the threshold for normal warnings
     * @return the validation result
     */
    public FormValidation doCheckNormalThreshold(@QueryParameter final String highThreshold, @QueryParameter final String normalThreshold) {
        return VALIDATION.validateNormal(highThreshold, normalThreshold);
    }
}