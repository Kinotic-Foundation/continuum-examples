import {CartItem} from '@/domain/CartItem'
import Product from '@/domain/Product'

/**
 * Holds all data for the shopping cart
 */
export class Cart {

    private itemMap: Map<number, CartItem> = new Map()

    private _itemCount = 0

    private _total = 0.0

    public get items(): CartItem[] {
        return Array.from(this.itemMap.values())
    }

    public addItem(product: Product, quantity: number): void {
        let cartItem = this.itemMap.get(product.id)
        if(!cartItem){
            cartItem = new CartItem(product, quantity)
            this.itemMap.set(product.id, cartItem)
        }else{
            cartItem.addQuantity(quantity)
        }
        this._total += product.price * quantity
        this._itemCount += quantity
    }

    public decrementItem(product: Product, quantity: number): void {
        const cartItem = this.itemMap.get(product.id)
        if(cartItem){
            cartItem.removeQuantity(quantity)
            this._total -= product.price * quantity
            this._itemCount -= quantity
        }
    }

    public removeItem(product: Product): void {
        const cartItem = this.itemMap.get(product.id)
        if(cartItem){
            this.itemMap.delete(product.id)
            this._itemCount -= cartItem.quantity
            this._total -= product.price * cartItem.quantity
            if(isNaN(this._total)){
                this._total = 0
            }
        }
    }

    public get itemCount(): number {
        return this._itemCount
    }

    public get total(): number {
        return this._total
    }

    public get cartItems(): CartItem[] {
        return Array.from(this.itemMap.values())
    }

    public emptyCart(): void {
        this.itemMap = new Map()
        this._itemCount = 0
        this._total = 0
    }
}
