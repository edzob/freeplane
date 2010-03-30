/**
 * 
 */
package org.freeplane.plugin.script.proxy;

import org.freeplane.features.common.map.NodeModel;
import org.freeplane.features.common.nodestyle.NodeStyleController;
import org.freeplane.features.common.nodestyle.NodeStyleModel;
import org.freeplane.features.mindmapmode.MModeController;
import org.freeplane.features.mindmapmode.nodestyle.MNodeStyleController;

class FontProxy extends AbstractProxy<NodeModel> implements Proxy.Font {
	FontProxy(final NodeModel delegate, final MModeController modeController) {
		super(delegate, modeController);
	}

	public String getName() {
		return getStyleController().getFontFamilyName(getDelegate());
	}

	public int getSize() {
		return getStyleController().getFontSize(getDelegate());
	}

	private MNodeStyleController getStyleController() {
		return (MNodeStyleController) NodeStyleController.getController(getModeController());
	}

	public boolean isBold() {
		return getStyleController().isBold(getDelegate());
	}

	public boolean isBoldSet() {
		return NodeStyleModel.isBold(getDelegate()) != null;
	}

	public boolean isItalic() {
		return getStyleController().isItalic(getDelegate());
	}

	public boolean isItalicSet() {
		return NodeStyleModel.isItalic(getDelegate()) != null;
	}

	public boolean isNameSet() {
		return NodeStyleModel.getFontFamilyName(getDelegate()) != null;
	}

	public boolean isSizeSet() {
		return NodeStyleModel.getFontSize(getDelegate()) != null;
	}

	public void resetBold() {
		getStyleController().setBold(getDelegate(), null);
	}

	public void resetItalic() {
		getStyleController().setItalic(getDelegate(), null);
	}

	public void resetName() {
		getStyleController().setFontFamily(getDelegate(), null);
	}

	public void resetSize() {
		getStyleController().setFontSize(getDelegate(), null);
	}

	public void setBold(final boolean bold) {
		getStyleController().setBold(getDelegate(), bold);
	}

	public void setItalic(final boolean italic) {
		getStyleController().setItalic(getDelegate(), italic);
	}

	public void setName(final String name) {
		getStyleController().setFontFamily(getDelegate(), name);
	}

	public void setSize(final int size) {
		getStyleController().setFontSize(getDelegate(), size);
	}
}
