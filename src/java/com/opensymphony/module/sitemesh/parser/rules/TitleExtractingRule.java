package com.opensymphony.module.sitemesh.parser.rules;

import com.opensymphony.module.sitemesh.HTMLPage;
import com.opensymphony.module.sitemesh.html.BlockExtractingRule;
import com.opensymphony.module.sitemesh.html.Tag;

public class TitleExtractingRule extends BlockExtractingRule {

    private final HTMLPage page;

    private boolean seenTitle;

    public TitleExtractingRule(HTMLPage page) {
        super(false, "title");
        this.page = page;
    }

    protected void end(Tag tag) {
        if (!seenTitle) {
            page.addProperty("title", context.currentBuffer().toString());
            seenTitle = true;
        }
    }
}