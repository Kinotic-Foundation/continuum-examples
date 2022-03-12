import {CartItem} from '@/domain/CartItem'
import Product from '@/domain/Product'

/**
 * Holds all data for the shopping cart
 */
export class Cart {

    private itemMap: Map<number, CartItem> = new Map()

    private _itemCount = 0

    private _total = 0.0

    public get items(): IterableIterator<CartItem> {
        return this.itemMap.values()
    }

    public addItem(product: Product, quantity: number): void{
        let cartItem = this.itemMap.get(product.id)
        if(!cartItem){
            cartItem = new CartItem(product, quantity)
            this.itemMap.set(product.id, cartItem)
        }
        cartItem.addQuantity(quantity)
        this._total += product.price * quantity
        this._itemCount += quantity
    }

    get itemCount(): number {
        return this._itemCount
    }

    public get total(): number {
        return this._total
    }
}