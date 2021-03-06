/*
 * VerifyRulesHandler.java
 *
 * Created on July 18, 2003, 12:26 AM
 */

package org.jboss.tools.common.verification.vrules.handler;

import org.eclipse.osgi.util.NLS;
import org.jboss.tools.common.verification.Messages;
import org.jboss.tools.common.verification.vrules.*;
import org.jboss.tools.common.verification.vrules.layer.VModelFactory;
import org.jboss.tools.common.model.*;
import org.jboss.tools.common.meta.action.*;
import java.util.*;

/**
 *
 * @author  valera
 */
public class VerifyRulesAllHandler extends VerifyHandler {
    private boolean gui = true;

    /** Creates a new instance of VerifyRulesHandler */
    public VerifyRulesAllHandler() {
    }

    public void executeHandler(XModelObject object, Properties p) throws XModelException {
        if(!gui) executeHandlerDefault(object, p);
        else executeHandlerGUI(object, p);
    }

    public boolean isEnabled(XModelObject object) {
        return VHelper.getManager() != null;
    }

    private void executeHandlerDefault(XModelObject object, Properties p) throws XModelException {
        if (!isEnabled(object)) return;
		VModel vmodel = VModelFactory.getModel(object.getModel());
        Map<String,List<VRule>> entities = new HashMap<String,List<VRule>>();
        VRuleSet[] ruleSets = VHelper.getManager().getRuleSets();
        for (int k = 0; k < ruleSets.length; k++) {
            if (!ruleSets[k].isEnabled()) continue;
            VRule[] rules = ruleSets[k].getRules();
            for (int i = 0; i < rules.length; i++) {
                VRule rule = rules[i];
                if (!rule.isEnabled() || rule.getAction() == null) continue;
                VEntity[] ent = rule.getEntities();
                for (int j = 0; j < ent.length; j++) {
                    String name = ent[j].getName();
                    List<VRule> r = entities.get(name);
                    if (r == null) {
                        r = new ArrayList<VRule>();
                        entities.put(name, r);
                    }
                    r.add(rule);
                }
            }
        }
        VObject vobject = vmodel.getObjectByPath(object.getPath());
        check(entities, vobject, object.getModel());
    }


    private void executeHandlerGUI(XModelObject object, Properties p) throws XModelException {

		SpecialWizard wizard = SpecialWizardFactory.createSpecialWizard("org.jboss.tools.common.verification.ui.vrules.wizard.runtime2.VerifyWizard"); //$NON-NLS-1$
        if(p == null) p = new Properties();
        String title = Messages.VerifyRulesAllHandler_ApplyVerificationRules;
        if(!object.getModelEntity().getName().equals("FileSystems")) //$NON-NLS-1$
          title = NLS.bind(Messages.VerifyRulesAllHandler_ApplyVerificationRulesForObject, 
        		  object.getPresentationString());
        p.setProperty("title", title); //$NON-NLS-1$
        p.setProperty("help", "ApplyVerificationRules"); //$NON-NLS-1$ //$NON-NLS-2$
        wizard.setObject(new Object[]{object, p});
        wizard.execute();
/*        
		GlobalBuilderImpl g = new GlobalBuilderImpl();
		g.setModel(object.getModel());
		g.execute(object);
*/
    }

}

