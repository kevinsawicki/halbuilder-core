package com.theoryinpractise.halbuilder;

import com.theoryinpractise.halbuilder.api.Link;
import com.theoryinpractise.halbuilder.api.RepresentationFactory;
import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class LinkTest {
    private RepresentationFactory representationFactory = new DefaultRepresentationFactory();

    private Link link = new Link(representationFactory, "http://example.com/", "rel", "name", "title", "hreflang");

    @Test
    public void equalLinksHaveEqualHashCodes() {
        Link otherLink = new Link(representationFactory, "http://example.com/", "rel", "name", "title", "hreflang");
        assertThat(link.hashCode()).isEqualTo(otherLink.hashCode());
    }

    @Test
    public void testHashCodeIsDependentOnHref() {
        Link otherLink = new Link(representationFactory, "http://example.com/other", "rel", "name", "title", "hreflang");
        assertThat(otherLink.hashCode()).isNotEqualTo(link.hashCode());
    }

    @Test
    public void testHashCodeIsDependentOnRel() {
        Link otherLink = new Link(representationFactory, "http://example.com/", "otherrel", "name", "title", "hreflang");
        assertThat(otherLink.hashCode()).isNotEqualTo(link.hashCode());
    }

    @Test
    public void testHashCodeIsDependentOnName() {
        Link otherLink = new Link(representationFactory, "http://example.com/", "rel", "othername", "title", "hreflang");
        assertThat(otherLink.hashCode()).isNotEqualTo(link.hashCode());
    }

    @Test
    public void testHashCodeIsDependentOnTitle() {
        Link otherLink = new Link(representationFactory, "http://example.com/", "rel", "name", "othertitle", "hreflang");
        assertThat(otherLink.hashCode()).isNotEqualTo(link.hashCode());
    }

    @Test
    public void testHashCodeIsDependentOnHreflang() {
        Link otherLink = new Link(representationFactory, "http://example.com/other", "rel", "name", "title", "hreflang");
        assertThat(otherLink.hashCode()).isNotEqualTo(link.hashCode());
    }

    @Test
    public void testEqualsIsDependentOnHref() {
        Link otherLink = new Link(representationFactory, "http://example.com/other", "rel", "name", "title", "hreflang");
        assertThat(otherLink).isNotEqualTo(link);
    }

    @Test
    public void testEqualsIsDependentOnRel() {
        Link otherLink = new Link(representationFactory, "http://example.com/", "otherrel", "name", "title", "hreflang");
        assertThat(otherLink).isNotEqualTo(link);
    }

    @Test
    public void testEqualsIsDependentOnName() {
        Link otherLink = new Link(representationFactory, "http://example.com/", "rel", "othername", "title", "hreflang");
        assertThat(otherLink).isNotEqualTo(link);
    }

    @Test
    public void testEqualsIsDependentOnTitle() {
        Link otherLink = new Link(representationFactory, "http://example.com/", "rel", "name", "othertitle", "hreflang");
        assertThat(otherLink).isNotEqualTo(link);
    }

    @Test
    public void testEqualsIsDependentOnHreflang() {
        Link otherLink = new Link(representationFactory, "http://example.com/other", "rel", "name", "title", "hreflang");
        assertThat(otherLink).isNotEqualTo(link);
    }

    @Test
    public void testToStringRendersHrefRel() {
        String toString = new Link(representationFactory, "http://example.com/other", "rel").toString();
        assertThat(toString).isEqualTo("<link rel=\"rel\" href=\"http://example.com/other\"/>");
    }

    @Test
    public void testToStringRendersHrefRelNameTitleHreflang() {
        String toString = link.toString();
        assertThat(toString).isEqualTo("<link rel=\"rel\" href=\"http://example.com/\" name=\"name\" title=\"title\" hreflang=\"hreflang\"/>");
    }

    @Test
    public void testHasTemplate() {
        Link templateLink = new Link(representationFactory, "http://example.com/search{?customerId}", "customerSearch");
        assertThat(templateLink.hasTemplate()).isTrue();
    }

    @Test
    public void testDoesNotHaveTemplate() {
        Link nonTemplateLink = new Link(representationFactory, "http://example.com/other", "rel");
        assertThat(nonTemplateLink.hasTemplate()).isFalse();
    }
}
