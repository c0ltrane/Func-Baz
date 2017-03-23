/* This file was generated by SableCC (http://www.sablecc.org/). */

package baz.syntax.node;

import baz.syntax.analysis.*;

@SuppressWarnings("nls")
public final class AAddAdditive extends PAdditive
{
    private PAdditive _left_;
    private TPlus _plus_;
    private PFactor _right_;

    public AAddAdditive()
    {
        // Constructor
    }

    public AAddAdditive(
        @SuppressWarnings("hiding") PAdditive _left_,
        @SuppressWarnings("hiding") TPlus _plus_,
        @SuppressWarnings("hiding") PFactor _right_)
    {
        // Constructor
        setLeft(_left_);

        setPlus(_plus_);

        setRight(_right_);

    }

    @Override
    public Object clone()
    {
        return new AAddAdditive(
            cloneNode(this._left_),
            cloneNode(this._plus_),
            cloneNode(this._right_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAAddAdditive(this);
    }

    public PAdditive getLeft()
    {
        return this._left_;
    }

    public void setLeft(PAdditive node)
    {
        if(this._left_ != null)
        {
            this._left_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._left_ = node;
    }

    public TPlus getPlus()
    {
        return this._plus_;
    }

    public void setPlus(TPlus node)
    {
        if(this._plus_ != null)
        {
            this._plus_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._plus_ = node;
    }

    public PFactor getRight()
    {
        return this._right_;
    }

    public void setRight(PFactor node)
    {
        if(this._right_ != null)
        {
            this._right_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._right_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._left_)
            + toString(this._plus_)
            + toString(this._right_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._left_ == child)
        {
            this._left_ = null;
            return;
        }

        if(this._plus_ == child)
        {
            this._plus_ = null;
            return;
        }

        if(this._right_ == child)
        {
            this._right_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._left_ == oldChild)
        {
            setLeft((PAdditive) newChild);
            return;
        }

        if(this._plus_ == oldChild)
        {
            setPlus((TPlus) newChild);
            return;
        }

        if(this._right_ == oldChild)
        {
            setRight((PFactor) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}