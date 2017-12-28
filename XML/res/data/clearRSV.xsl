<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

    <xsl:template match="/">
        <xsl:copy>
            <xsl:apply-templates select="node()"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="node()">
        <xsl:copy>
            <xsl:apply-templates/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="РасчетСВ/ОбязПлатСВ/РасчСВ_ОПС_ОМС/РасчСВ_ОПС/ВыплНачислФЛ">
        <xsl:copy-of select="descendant-or-self::*"/>
    </xsl:template>

    <xsl:template match="РасчетСВ/ОбязПлатСВ/РасчСВ_ОПС_ОМС/РасчСВ_ОМС/ВыплНачислФЛ">
        <xsl:copy-of select="descendant-or-self::*"/>
    </xsl:template>

    <xsl:template match="РасчетСВ/ОбязПлатСВ/РасчСВ_ОСС.ВНМ/ВыплНачислФЛ/ВыплНачислФЛ">
        <xsl:copy-of select="descendant-or-self::*"/>
    </xsl:template>

    <xsl:template match="РасчетСВ/ОбязПлатСВ/РасчСВ_ОПС_ОМС/РасчСВ_ОПС/НеОбложенСВ">
        <xsl:copy-of select="descendant-or-self::*"/>
    </xsl:template>

    <xsl:template match="РасчетСВ/ОбязПлатСВ/РасчСВ_ОПС_ОМС/РасчСВ_ОМС/НеОбложенСВ">
        <xsl:copy-of select="descendant-or-self::*"/>
    </xsl:template>

    <xsl:template match="РасчетСВ/ОбязПлатСВ/РасчСВ_ОСС.ВНМ/ВыплНачислФЛ/НеОбложенСВ">
        <xsl:copy-of select="descendant-or-self::*"/>
    </xsl:template>


    <xsl:template match="РасчетСВ/ПерсСвСтрахЛиц/СвВыплСВОПС/СвВыпл">
        <xsl:copy-of select="descendant-or-self::*"/>
    </xsl:template>

    <xsl:template match="РасчетСВ/ПерсСвСтрахЛиц/ДанФЛПолуч">
        <xsl:copy>
            <xsl:copy-of select="attribute::СНИЛС"/>
            <xsl:copy-of select="ФИО"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="@СНИЛС">
        <xsl:attribute name="a"/>

    </xsl:template>

</xsl:stylesheet>